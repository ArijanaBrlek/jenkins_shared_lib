
def call(lintDirectories){
    def directories_to_lint = joinArray(lintDirectories)
    sh "pylint --output-format=parseable ${directories_to_lint} > pylint.log || exit 0"
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
