def call(Map pipelineParams) {
    node('master') {
        ws("/var/jenkins_home/workspace/testing_jenkinsfile")
        {
            stage('Git checkout') {
                checkoutGitRepository()
            }

            stage('Lint') {
                docker.image('jenkins_build_image').inside('-u root'){
                    runLinting(pipelineParams.lintDirectory)
                }
            }

            stage('Build'){
                docker.image('jenkins_build_image').inside('-u root'){
                echo 'Build.'
                }
            }
        }
    }
}