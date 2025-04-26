package com.example.myyyyapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myyyyapplication.presentation.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private val splashDelay: Long = 2000 // Затримка в мілісекундах

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Запускаємо корутину для затримки та переходу на головний екран
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashDelay)
            startMainActivity()
            finish() // Закриваємо SplashActivity, щоб користувач не міг повернутися
        }

        // Тут можна також виконувати інші ініціалізації у фоновому потоці
        // наприклад, завантаження даних, перевірку мережі тощо.
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}