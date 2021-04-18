package com.aakash.fithub

import com.aakash.fithub.api.ServiceBuilder
import com.aakash.fithub.bmi.BMIFormula
import com.aakash.fithub.entity.User
import com.aakash.fithub.repository.UserRepository
import com.aakash.fithub.repository.WorkOutRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


private lateinit var arithmetic: BMIFormula

class BMICalculateTest {
    private var repository= UserRepository()
//    @Test
//    fun checkRegisterUSer() = runBlocking {
//        val user =
//                User(fname = "this is test", email = "thisisemail1mdnsshf212@gmail.com",password = "testing12")
//        repository = UserRepository()
//        val expectedResult = true
//        val response = repository.registerUSer(user)
//        val actualResult = response.success!!
//        Assert.assertEquals(expectedResult, actualResult)
//    }


    @Test
    fun checkRegisterUSer() = runBlocking {
        val user =User(fname = "this is test", email = "thisisemail1mdnsshf2dfyfgh12@gmail.com",password = "testing12")
        repository = UserRepository()
        val expectedResult = true
        val response = repository.registerUSer(user)
        val actualResult = response.success!!
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun loginUser() = runBlocking {
        val user = User(email = "aks@gmail.com", password = "12345")
        repository = UserRepository()
        val expectedResult = true
        val response = repository.checkUser(user)
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun getme() = runBlocking {
        val user = User(email = "aks@gmail.com", password = "12345")
        repository = UserRepository()
        val expectedResult = true
        val response = repository.checkUser(user)
        ServiceBuilder.token = "Bearer " + response.token
        ServiceBuilder.id = response.id
        if (response.success == true) {
            val actualResult = repository.getUser(ServiceBuilder.id!!).success
            Assert.assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun checkBMI()=runBlocking {
        val user = User(email = "aks@gmail.com", password = "12345")
        repository = UserRepository()
        val expectedResult = false
        val response = repository.checkUser(user)
        ServiceBuilder.token = "Bearer " + response.token
        ServiceBuilder.id = response.id
        val repo=WorkOutRepository()
        val actualResult=repo.getallProduct(ServiceBuilder.id!!).success
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun getAllWorkout()= runBlocking {
        val user = User(email = "aks@gmail.com", password = "12345")
        repository = UserRepository()
        val expectedResult = true
        val response = repository.checkUser(user)
        ServiceBuilder.token = "Bearer " + response.token
        ServiceBuilder.id = response.id
        val repo=WorkOutRepository()
        val actualResult=repo.getWorkOutApiData().success
        Assert.assertEquals(expectedResult, actualResult)
    }
    @Test
    fun getAllbookmarkedWorkout()= runBlocking {
        val user = User(email = "aks@gmail.com", password = "12345")
        repository = UserRepository()
        val expectedResult = true
        val response = repository.checkUser(user)
        ServiceBuilder.token = "Bearer " + response.token
        ServiceBuilder.id = response.id
        val repo=WorkOutRepository()
        val actualResult=repo.getallProduct(ServiceBuilder.id!!).success
        Assert.assertEquals(expectedResult, actualResult)
    }


}