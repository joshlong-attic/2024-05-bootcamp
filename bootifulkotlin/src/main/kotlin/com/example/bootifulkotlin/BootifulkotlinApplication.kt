package com.example.bootifulkotlin

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans
import org.springframework.stereotype.Component


@SpringBootApplication
class BootifulkotlinApplication


// [Component]



@Component // annotations
class MyRunner : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        println("hello Lou!!!")
    }

}

fun main(args: Array<String>) {
    
    val list : List<String> = listOf("1", "2")
    
    val name:String?  = null
    
    println(name)
    
    runApplication<BootifulkotlinApplication>(*args) {
        addInitializers(
            beans {
                bean {
                    ApplicationRunner {
                        println("hello Lou!")
                    }
                }

            }
        )
    }
}

