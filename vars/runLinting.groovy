
def call(lintDirectories){
    println lintDirectories.join(' ')
    sh "pylint --output-format=parseable ${lintDirectories.join(' ')} > pylint.log || exit 0"
    sh 'cat pylint.log'

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
            parserName: 'PYLint',
            pattern   : 'pylint.log'
        ]],
        healthy: '0',
        canResolveRelativePaths: true,
        usePreviousBuildAsReference: true
    ])
}

@NonCPS
def joinArray(lintDirectories){
    println lintDirectories.join(' ')
    return lintDirectories.join(' ')
}
