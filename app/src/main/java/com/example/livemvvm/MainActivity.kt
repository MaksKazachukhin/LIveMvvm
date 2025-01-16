package com.example.livemvvm
import MainViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        // Подписка на изменение counterData
        viewModel.counterData.observe(this, Observer { counter ->
            textView.text = counter.toString()
        })

        // Подписка на изменение цвета текста
        viewModel.textColor.observe(this, Observer { color ->
            textView.setTextColor(color)
        })

        // Подписка на сообщение Toast
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.resetToastMessage() // Сбрасываем сообщение, чтобы оно показывалось один раз
            }
        })

        // Обработчик кнопки
        button.setOnClickListener {
            viewModel.incrementCounter()
        }
    }
}
