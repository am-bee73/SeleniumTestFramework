#!Groovy

pipeline {
    agent any
    stages {
        stage("mvn test") {
            steps {
                script {
                    sh """
                        cd /c/Users/aiordan/IdeaProjects/master/SeleniumTestFramework/test-framework/selenium-web-testing/
                        /c/Maven/bin/mvn.cmd clean test 
                        """
                }
            }
        }
        stage("Allure generate") {
            steps {
                script {
                    sh """
                        cd /c/Users/aiordan/IdeaProjects/master/SeleniumTestFramework/test-framework/selenium-web-testing/
                        /c/Users/aiordan//scoop//shims//allure generate allure-results --clean -o allure-report
                        """
                }
            }
        }
        stage("Allure serve") {
            steps {
                script {
                    sh """
                        cd /c/Users/aiordan/IdeaProjects/master/SeleniumTestFramework/test-framework/selenium-web-testing/
                        /c/Users/aiordan//scoop//shims//allure serve -h localhost
                        """
                }
            }
        }
    }
}