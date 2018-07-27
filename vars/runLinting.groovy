
def call(lintDirectory){
    sh "pylint --output-format=parseable ${lintDirectory} > pylint.log || exit 0"
    sh "pylint --rcfile=/etc/pylintrc --output-format=parseable ${lintDirectory} > pylint.log || exit 0"
    sh 'cat /etc/pylintrc'
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
