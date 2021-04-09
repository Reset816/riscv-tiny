package ALU

import chisel3._
import chisel3.util._
import chisel3.iotesters._
import org.scalatest.{Matchers, FlatSpec}

class ALUTest(c: ALU) extends PeekPokeTester(c) {
  println("\nADD")
  poke(c.io.op, 0)
  poke(c.io.in0, 2)
  poke(c.io.in1, 3)
  peek(c.io.out)
  expect(c.io.out, 5)

  println("\nADD")
  poke(c.io.op, 0)
  poke(c.io.in0, -2)
  poke(c.io.in1, 3)
  peek(c.io.out)
  expect(c.io.out, 1)

  step(1)
  println("\nSUB")
  poke(c.io.op, 1)
  poke(c.io.in0, 2)
  poke(c.io.in1, 3)
  peek(c.io.out)
  //expect(c.io.out, -1)
  expect(c.io.out, 4294967295L)
  //expect(c.io.out, -1.S(32.W).asUInt().toInt)

  step(1)
  println("\nSUB")
  poke(c.io.op, 1)
  poke(c.io.in0, -2)
  poke(c.io.in1, -3)
  peek(c.io.out)
  expect(c.io.out, 1)

  step(1)
  println("\nSUB")
  poke(c.io.op, 1)
  poke(c.io.in0, 7)
  poke(c.io.in1, 3)
  peek(c.io.out)
  expect(c.io.out, 4)

  step(1)
  println("\nAND")
  poke(c.io.op, 2)
  poke(c.io.in0, 12)
  poke(c.io.in1, 10)
  peek(c.io.out)
  expect(c.io.out, 8) // 1100 & 1010 = 1000

  step(1)
  println("\nOR")
  poke(c.io.op, 3)
  poke(c.io.in0, 12)
  poke(c.io.in1, 10)
  peek(c.io.out)
  expect(c.io.out, 14) // 1100 & 1010 = 1110

  step(1)
  println("\nXOR")
  poke(c.io.op, 4)
  poke(c.io.in0, 12)
  poke(c.io.in1, 10)
  peek(c.io.out)
  expect(c.io.out, 6) // 1100 & 1010 = 0110

  step(1)
  println("\nSLL")
  poke(c.io.op, 5)
  poke(c.io.in0, 10)
  poke(c.io.in1, 2)
  peek(c.io.out)
  expect(c.io.out, 40) // 1010 < 2 = 101000

  step(1)
  println("\nSLL")
  poke(c.io.op, 5)
  poke(c.io.in0, 1)
  poke(c.io.in1, 32)
  peek(c.io.out)
  expect(c.io.out, 0) // 1 < 32 = 0

  step(1)
  println("\nSRA")
  poke(c.io.op, 6)
  poke(c.io.in0, 6)
  poke(c.io.in1, 1)
  peek(c.io.out)
  expect(c.io.out, 3) // 0110 > 1 = 0011

  step(1)
  println("\nSRA")
  poke(c.io.op, 6)
  poke(c.io.in0, 2147483648L)
  poke(c.io.in1, 1)
  peek(c.io.out)
  expect(c.io.out, 3221225472L) // 2^31 + 1 = 3221225472

  step(1)
  println("\nSRL")
  poke(c.io.op, 7)
  poke(c.io.in0, 10)
  poke(c.io.in1, 2)
  peek(c.io.out)
  expect(c.io.out, 2) // 1010 > 2 = 2

  step(1)
  println("\nSLT")
  poke(c.io.op, 8)
  poke(c.io.in0, 0)
  poke(c.io.in1, 1)
  peek(c.io.out)
  expect(c.io.out, 1) //

  step(1)
  println("\nSLTU")
  poke(c.io.op, 9)
  poke(c.io.in0, 0)
  poke(c.io.in1, 1)
  peek(c.io.out)
  expect(c.io.out, 1) //

  step(1)
  println("\nBEQ")
  poke(c.io.op, 10)
  poke(c.io.in0, 0)
  poke(c.io.in1, 1)
  peek(c.io.flag)
  expect(c.io.flag, 0) // 0 != 1  flag = false

  step(1)
  println("\nBEQ")
  poke(c.io.op, 10)
  poke(c.io.in0, 8)
  poke(c.io.in1, 8)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 8 = 8  flag = true

  step(1)
  println("\nBNE")
  poke(c.io.op, 11)
  poke(c.io.in0, 0)
  poke(c.io.in1, 1)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 0 != 1  flag = true

  step(1)
  println("\nBNE")
  poke(c.io.op, 11)
  poke(c.io.in0, 8)
  poke(c.io.in1, 8)
  peek(c.io.flag)
  expect(c.io.flag, 0) // 8 = 8  flag = false

  step(1)
  println("\nBLT")
  poke(c.io.op, 12)
  poke(c.io.in0, -8)
  poke(c.io.in1, 1)
  peek(c.io.flag)
  expect(c.io.flag, 1) // -8 < 1  flag = true

  step(1)
  println("\nBLT")
  poke(c.io.op, 12)
  poke(c.io.in0, 1)
  poke(c.io.in1, -8)
  peek(c.io.flag)
  expect(c.io.flag, 0) // 1 > -8  flag = false

  step(1)
  println("\nBLTU")
  poke(c.io.op, 13)
  poke(c.io.in0, 3)
  poke(c.io.in1, 1)
  peek(c.io.flag)
  expect(c.io.flag, 0) // 3 > 1  flag = false

  step(1)
  println("\nBLTU")
  poke(c.io.op, 13)
  poke(c.io.in0, 1)
  poke(c.io.in1, 3)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 1 < 3  flag = true

  step(1)
  println("\nBGE")
  poke(c.io.op, 14)
  poke(c.io.in0, -8)
  poke(c.io.in1, 1)
  peek(c.io.flag)
  expect(c.io.flag, 0) // -8 < 1  flag = false

  step(1)
  println("\nBGE")
  poke(c.io.op, 14)
  poke(c.io.in0, 1)
  poke(c.io.in1, -8)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 1 > -8  flag = true

  step(1)
  println("\nBGE")
  poke(c.io.op, 14)
  poke(c.io.in0, -8)
  poke(c.io.in1, -8)
  peek(c.io.flag)
  expect(c.io.flag, 1) // -8 = -8  flag = true

  step(1)
  println("\nBGEU")
  poke(c.io.op, 15)
  poke(c.io.in0, 3)
  poke(c.io.in1, 5)
  peek(c.io.flag)
  expect(c.io.flag, 0) // 3 < 5  flag = false

  step(1)
  println("\nBGEU")
  poke(c.io.op, 15)
  poke(c.io.in0, 5)
  poke(c.io.in1, 3)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 5 > 3  flag = true

  step(1)
  println("\nBGEU")
  poke(c.io.op, 15)
  poke(c.io.in0, 8)
  poke(c.io.in1, 8)
  peek(c.io.flag)
  expect(c.io.flag, 1) // 8 = 8  flag = true
}