
def call(lintDirectories){
    sh "pylint --output-format=parseable ${lintDirectories.join(' ')} > pylint.log || exit 0"
    sh "pylint --output-format=parseable ${lintDirectories.join(' ')} > pylint_test.log || exit 0"
    sh 'cat pylint.log'
    sh 'cat pylint_test.log'

    step([
        $class: 'WarningsPublisher',
        parserConfigurations: [[
            parserName: 'PYLint',
            pattern   : 'pylint_*.log'
        ]],
        healthy: '0',
        canResolveRelativePaths: true,
        usePreviousBuildAsReference: true
    ])
}