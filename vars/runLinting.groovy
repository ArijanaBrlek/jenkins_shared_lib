
def call(lintDirectories){
    def directories = lintDirectories.join(' ')
    sh "pylint --output-format=parseable ${directories} > pylint.log || exit 0"
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
