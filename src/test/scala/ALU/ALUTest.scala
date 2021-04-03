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
  expect(c.io.out, 2)

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
  println("\nSRL")
  poke(c.io.op, 7)
  poke(c.io.in0, 10)
  poke(c.io.in1, 2)
  peek(c.io.out)
  expect(c.io.out, 2) // 1010 > 2 = 2
}