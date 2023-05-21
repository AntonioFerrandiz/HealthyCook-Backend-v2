pipeline {
  agent any
  stages {
    stage('Check for POM') {
      steps {
        fileExists 'pom.xml'
      }
    }

    stage('Build with Maven') {
      steps {
        bat 'mvn clean package -Dmaven.test.skip'
      }
    }

    stage('Post Build Steps') {
      steps {
        writeFile(file: 'status.txt', text: '????')
      }
    }

  }
}
