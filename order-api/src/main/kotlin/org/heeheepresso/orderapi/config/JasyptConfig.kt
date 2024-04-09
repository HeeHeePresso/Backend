package org.heeheepresso.orderapi.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!test")
@EnableEncryptableProperties
class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    fun jasyptEncryptor(): PooledPBEStringEncryptor {
        val pooledPBEStringEncryptor = PooledPBEStringEncryptor()

        val password = System.getenv("JASYPT_PASSWORD").ifEmpty { "test" }
        val algorithm = System.getenv("JASYPT_ALGORITHM")
        return pooledPBEStringEncryptor.apply {
            setAlgorithm(algorithm)
            setPoolSize(1)
            setStringOutputType("base64")
            setKeyObtentionIterations(1000)
            setPassword(password.orEmpty())
        }
    }


}