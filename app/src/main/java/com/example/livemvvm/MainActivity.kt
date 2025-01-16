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

        viewModel.counterData.observe(this, Observer { counter ->
            textView.text = counter.toString()
        })

        // меняю цвет))
        viewModel.textColor.observe(this, Observer { color ->
            textView.setTextColor(color)
        })

        // сообщение вылезает
        viewModel.toastMessage.observe(this, Observer { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                viewModel.resetToastMessage() // Сбрасываем сообщение, чтобы оно показывалось один раз
            }
        })

        // Обработка кнопочки
        button.setOnClickListener {
            viewModel.incrementCounter()
        }
    }
}
