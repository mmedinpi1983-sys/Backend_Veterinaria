param([string]$modulesDir)

$allModuleDirs = Get-ChildItem -LiteralPath $modulesDir -Directory | Sort-Object Name

foreach ($modDir in $allModuleDirs) {
    $mod = $modDir.Name
    $files = @(
        (Join-Path $modDir.FullName "${mod}Service.java"),
        (Join-Path $modDir.FullName "${mod}ServiceImpl.java"),
        (Join-Path $modDir.FullName "${mod}Repository.java"),
        (Join-Path $modDir.FullName "${mod}Controller.java")
    )
    
    foreach ($f in $files) {
        if (-not (Test-Path $f)) { continue }
        $content = Get-Content -Path $f -Raw -Encoding UTF8
        
        $types = @("Response","Request","ListItem","ListResponse")
        $usedTypes = @()
        
        # First pass: replace qualified refs with unqualified on NON-import lines
        $lines = $content -split "`r?`n"
        $newLines = @()
        foreach ($line in $lines) {
            $isImport = ($line -match '^import ')
            foreach ($t in $types) {
                $pattern = [regex]::Escape("${mod}DTO.$t")
                if ($line -match $pattern) {
                    if (-not $isImport) {
                        $line = $line -replace $pattern, $t
                        if ($t -notin $usedTypes) { $usedTypes += $t }
                    }
                }
            }
            $newLines += $line
        }
        $content = $newLines -join "`n"
        
        if ($usedTypes.Count -eq 0) { continue }
        
        # Build imports
        $importLines = @()
        foreach ($t in $types) {
            if ($t -in $usedTypes) {
                $importLines += "import app.veterinaria.api_veterianria.modulos.$mod.${mod}DTO.$t;"
            }
        }
        
        # Insert imports after package or after last existing import
        $lastImportIdx = -1
        for ($i = 0; $i -lt $newLines.Count; $i++) {
            if ($newLines[$i] -match '^import ') { $lastImportIdx = $i }
        }
        
        if ($lastImportIdx -ge 0) {
            # Insert after last import
            $before = $newLines[0..$lastImportIdx]
            $after = $newLines[($lastImportIdx+1)..($newLines.Count-1)]
            $content = ($before + $importLines + $after) -join "`n"
        } else {
            # No imports found, insert after package
            for ($i = 0; $i -lt $newLines.Count; $i++) {
                if ($newLines[$i] -match '^package ') {
                    $before = $newLines[0..$i]
                    $after = $newLines[($i+1)..($newLines.Count-1)]
                    $content = ($before + $importLines + $after) -join "`n"
                    break
                }
            }
        }
        
        # Clean up multiple blank lines
        $content = $content -replace "`n{3,}", "`n`n"
        $content = $content.TrimEnd() + "`n"
        
        [System.IO.File]::WriteAllText($f, $content, [System.Text.UTF8Encoding]::new($false))
        Write-Output "${mod}/$(Split-Path $f -Leaf): $($usedTypes -join ', ')"
    }
}
