package test_basic


import chisel3._
import chisel3.util._
import chisel3.iotesters._
import org.scalatest.{Matchers, FlatSpec}

class ANDTest(c: AND) extends PeekPokeTester(c) {
  poke(c.io.a, 1)
  poke(c.io.b, 0)
  peek(c.io.c)
  step(1)
  expect(c.io.c, 0)
}