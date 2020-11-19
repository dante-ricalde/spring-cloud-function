import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.transformers.*
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
	id("org.springframework.boot") version "2.4.0"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("com.github.johnrengelman.shadow") version "6.1.0"
	id("org.springframework.boot.experimental.thin-launcher") version "1.0.25.RELEASE"
	id("net.ltgt.apt-idea") version "0.21"
	kotlin("jvm") version "1.4.10"
	kotlin("plugin.spring") version "1.4.10"
}

apply(plugin = "net.ltgt.apt-idea")

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springCloudVersion"] = "2020.0.0-SNAPSHOT"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-function-context")
	implementation("org.springframework.cloud:spring-cloud-starter-function-webflux")
	implementation("org.springframework.cloud:spring-cloud-function-adapter-aws")
	//implementation("org.springframework.cloud:spring-cloud-function-kotlin")
	// This dependency brings KotlinLambdaToFunctionAutoConfiguration to convert Kotlin lambdas to Java lambdas (Consumer, Function, Supplier)
	//implementation("org.springframework.cloud:spring-cloud-function-kotlin:3.0.11.RELEASE")
	/*implementation("com.amazonws:aws-lambda-java-core:1.2.0")
	implementation("com.amazonws:aws-lambda-java-events:2.2.6")
	implementation("com.amazonaws:aws-java-sdk-s3:1.11.557")*/
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks {
	named<ShadowJar>("shadowJar") {
		classifier = "aws"
		dependencies {
			exclude(
					dependency("org.springframework.cloud:spring-cloud-function-web"))
		}
		// Required for Spring
		mergeServiceFiles()
		append("META-INF/spring.handlers")
		append("META-INF/spring.schemas")
		append("META-INF/spring.tooling")
		transform(PropertiesFileTransformer::class.java) {
			paths = listOf("META-INF/spring.factories")
			mergeStrategy = "append"
		}
	}
	assemble {
		//dependsOn(shadowJar)
		dependsOn(shadowJar, thinJar)
	}
	jar {
		manifest {
			attributes(mapOf("Main-Class" to "com.example.functionsample.FunctionSampleApplication", "Starts-Class" to "com.example.functionsample.FunctionSampleApplication"))
		}
	}
}

/*idea {
	module {
		downloadJavadoc = true
		downloadSources = true
	}
}*/