
def call(lintDirectories){
    sh "pylint --output-format=parseable hello_world >> pylint.log || exit 0"
    sh "pylint --output-format=parseable hello_world_2 > pylint_test.log || exit 0"
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