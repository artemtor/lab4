import kotlin.random.Random
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    do {
        println("Камень-ножницы-бумага")
        println("Правила:")
        println("1. Камень побеждает ножницы \n2. Ножницы побеждают бумагу \n3. Бумага побеждает камень")
        val playerInput = getPlayerChoice()
        val computerInput = getComputerChoice()
        val result = determineWinner(playerInput, computerInput)
        println("Твой выбор: ${getChoiceString(playerInput)}")
        println("Выбор компьютера: ${getChoiceString(computerInput)}")

        println(result)
        print("Продолжить игру? (д/н): ")
        val userInput = scanner.nextLine()
        if (userInput.equals("н", ignoreCase = true)) {
            println("Программа завершена.")
            break
        }
        val continueInput = scanner.nextLine()
    } while (!continueInput.equals("н", ignoreCase = true))
}

fun getPlayerChoice(): Choice {
    println("Выбери свой вариант:")
    println("[1] Камень")
    println("[2] Ножницы")
    println("[3] Бумага")

    val input = readLine()?.toIntOrNull()

    return when (input) {
        1 -> Choice.ROCK
        2 -> Choice.SCISSORS
        3 -> Choice.PAPER
        else -> {
            println("Некорректный ввод, попробуй еще раз")
            getPlayerChoice()
        }
    }
}

fun getComputerChoice(): Choice {
    val randomNum = Random.nextInt(1, 4)
    return when (randomNum) {
        1 -> Choice.ROCK
        2 -> Choice.SCISSORS
        else -> Choice.PAPER
    }
}

fun getChoiceString(choice: Choice): String {
    return when (choice) {
        Choice.ROCK -> "Камень"
        Choice.SCISSORS -> "Ножницы"
        Choice.PAPER -> "Бумага"
    }
}

fun determineWinner(playerChoice: Choice, computerChoice: Choice): String {
    if (playerChoice == computerChoice) {
        return "Ничья, игра переигрывается"
    }

    return if (
        (playerChoice == Choice.ROCK && computerChoice == Choice.SCISSORS) ||
        (playerChoice == Choice.SCISSORS && computerChoice == Choice.PAPER) ||
        (playerChoice == Choice.PAPER && computerChoice == Choice.ROCK)
    ) {
        "Ты победил!"
    } else {
        "Компьютер победил!"
    }
}

enum class Choice {
    ROCK,
    SCISSORS,
    PAPER
}