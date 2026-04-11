package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Build : BuildType({
    name = "Build"

  
    artifactRules = "target/*.jar => artifacts/"
    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(HttpsGithubComYuriKopshevTeamcityRefsHeadsMain)
    }

    steps {
        maven {
            id = "Maven2"
            conditions {
                equals("teamcity.build.branch", "main")
            }
          
            goals = "clean package"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
            userSettingsSelection = "settings.xml"
        }
        maven {
            id = "Maven2_1"
            goals = "clean test"
        }
    }

    triggers {
        vcs {}
    }
    
    features {
        perfmon {}
    }
})
