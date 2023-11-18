import kotlin.random.Random
import java.util.Scanner
fun main() {
    val scanner = Scanner(System.`in`)
    do {
        // Ввод исходного сообщения
        print("Введите исходное сообщение: ")
        val message = readLine()!!.toUpperCase()

        // Ввод ключа
        print("Введите ключ: ")
        val key = readLine()!!.toUpperCase()

        // Выбор типа таблицы
        print("Использовать случайную таблицу? (д/н): ")
        val useRandomTable = readLine()?.equals("д", ignoreCase = true) == true

        // Получение шифровальной таблицы
        val cipherTable = if (useRandomTable) generateRandomTable() else generateTypicalTable()

        // Вывод исходного сообщения и ключа
        println("Исходное сообщение: $message")
        println("Ключ: $key")

        // Шифрование сообщения
        val encryptedMessage = encrypt(message, key, cipherTable)

        // Вывод зашифрованного сообщения
        println("Зашифрованное сообщение: $encryptedMessage")

        // Вывод шифровальной таблицы
        println("Шифровальная таблица:")
        printTable(cipherTable)

        print("Продолжить? (д/н): ")
        val userInput = scanner.nextLine()
        if (userInput.equals("н", ignoreCase = true)) {
            println("Программа завершена.")
            break
        }
        val continueInput = scanner.nextLine()
    } while (!continueInput.equals("н", ignoreCase = true))
}

// Генерация случайной шифровальной таблицы
fun generateRandomTable(): Array<CharArray> {
    val alphabet = ('А'..'Я').toList().toCharArray()
    val shuffledAlphabet = alphabet.copyOf()
    shuffledAlphabet.shuffle()

    val table = Array(32) { CharArray(32) }
    for (i in 0 until 32) {
        for (j in 0 until 32) {
            table[i][j] = shuffledAlphabet[(i + j) % 32]
        }
    }
    return table
}

// Генерация типовой шифровальной таблицы
fun generateTypicalTable(): Array<CharArray> {
    val alphabet = ('А'..'Я').toList().toCharArray()

    val table = Array(32) { CharArray(32) }
    for (i in 0 until 32) {
        for (j in 0 until 32) {
            table[i][j] = alphabet[(i + j) % 32]
        }
    }
    return table
}

// Шифрование сообщения
fun encrypt(message: String, key: String, table: Array<CharArray>): String {
    val encryptedMessage = StringBuilder()
    for (i in message.indices) {
        val messageChar = message[i]
        val keyChar = key[i % key.length]
        val rowIndex = table[0].indexOf(keyChar)
        val colIndex = table.map { it[0] }.indexOf(messageChar)
        encryptedMessage.append(table[rowIndex][colIndex])
    }
    return encryptedMessage.toString()
}

// Вывод шифровальной таблицы
fun printTable(table: Array<CharArray>) {
    for (row in table) {
        println(row.joinToString(" "))
    }
}