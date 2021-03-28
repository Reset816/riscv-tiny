//// addertest.scala
//package ALU
//
//import chisel3._
//import chisel3.util._
//import chisel3.iotesters._
//import org.scalatest.{Matchers, FlatSpec}
//
//class ALUTest1(c: ALU) extends PeekPokeTester(c) {
//    poke(c.io.op, 0)
//    poke(c.io.in0, 1)
//    poke(c.io.in1, 1)
//    peek(c.io.out)
//}