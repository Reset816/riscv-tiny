// AND.scala
package test_basic

import chisel3._

class AND extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(1.W))
    val b = Input(UInt(1.W))
    val c = Output(UInt(1.W))
    val d = Input(UInt(1.W))
  })

  io.c := io.a & io.b & io.d
}