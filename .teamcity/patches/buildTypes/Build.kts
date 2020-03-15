package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.GradleBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build")) {
    check(artifactRules == "build/libs/devcordbot-*.jar") {
        "Unexpected option value: artifactRules = $artifactRules"
    }
    artifactRules = """
        build/libs/devcordbot-*.jar
        rankmigrator/build/libs/rankmigrator-*.jar
    """.trimIndent()

    expectSteps {
        gradle {
            tasks = "clean build"
            buildFile = ""
            gradleWrapperPath = ""
        }
    }
    steps {
        update<GradleBuildStep>(0) {
            jdkHome = "%env.JDK_13_x64%"
        }
    }
}