package com.example.customviews.tictactoe

import androidx.appcompat.app.AppCompatActivity

enum class Cell {
    X, O, EMPTY
}

typealias OnFieldChangedListener = (field: TicTacToeField) -> Unit

class TicTacToeField(
    val rows: Int,
    val columns: Int
) : AppCompatActivity() {
    private val cells = Array(rows) { Array(columns) { Cell.EMPTY } }
    val listeners = mutableSetOf<OnFieldChangedListener>()

    private fun getCell(row: Int, column: Int): Cell {
        if (row < 0 || column < 0 || row >= rows || column >= columns) throw IndexOutOfBoundsException()
        return cells[row][column]
    }

    private fun setCell(row: Int, column: Int, value: Cell) {
        var cell = getCell(row, column)
        if (cell != value) {
            cell = value
            listeners.forEach { it.invoke(this) }
        }
    }
}