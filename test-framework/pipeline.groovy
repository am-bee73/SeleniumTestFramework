#!Groovy

pipeline {
    agent any
    
       tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Apache Maven 3.6.3"
    }
    
    stages {
        
        stage("mvn test") {
            steps {
                script {
                    sh """
                       ls
                       pwd
                        cd test-framework/selenium-web-testing/
                        mvn -Dmaven.test.failure.ignore=true clean test 
                        """
                }
            }
        }

        stage('reports') {
            steps {
                script {
                    allure([
                            includeProperties: false,
                            jdk: '',
                            properties: [],
                            reportBuildPolicy: 'ALWAYS',
                            results: [[path: 'test-framework/selenium-web-testing/target/allure-results']]
                    ])
                }
            }
        }
    }
}
