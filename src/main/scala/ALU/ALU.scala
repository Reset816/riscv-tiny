package ALU

import chisel3.util.switch
import chisel3._
import chisel3.util.is

class ALU extends Module {
  val io = IO(new Bundle {
    val op = Input(UInt(4.W)) // op from ALU control
    val in0 = Input(UInt(32.W))
    val in1 = Input(UInt(32.W))
    var out = Output(UInt(32.W))
    var flag = Output(Bool())
  })

  io.out := 0.U
  io.flag := false.B

  val shift: UInt = io.in1(5, 0).asUInt()

  switch(io.op) {
    is("b0000".U) { // ADD
      //io.out := (io.in0.asSInt() + io.in1.asSInt()).asUInt()
      io.out := io.in0 + io.in1
    }
    is("b0001".U) { // SUB
      //io.out := (io.in0.asSInt() - io.in1.asSInt()).asUInt()
      io.out := io.in0 - io.in1
    }
    is("b0010".U) { // AND
      io.out := io.in0 & io.in1
    }
    is("b0011".U) { // OR
      io.out := io.in0 | io.in1
    }
    is("b0100".U) { // XOR
      io.out := io.in0 ^ io.in1
    }
    is("b0101".U) { // SLL: Shift Left Logical
      io.out := io.in0 << shift
    }
    is("b0110".U) { // SRA: Shift Right Arithmetic
      io.out := (io.in0.asSInt() >> shift).asUInt()
    }
    is("b0111".U) { // SRL: Shift Right Logical
      io.out := io.in0 >> shift
    }
    is("b1000".U) { // SLT
      switch(io.in0.asSInt() < io.in1.asSInt()) {
        is(true.B) {
          io.out := 1.U(32.W)
        }
        is(false.B) {
          io.out := 0.U(32.W)
        }
      }
    }
    is("b1001".U) { // SLTU
      switch(io.in0 < io.in1) {
        is(true.B) {
          io.out := 1.U(32.W)
        }
        is(false.B) {
          io.out := 0.U(32.W)
        }
      }
    }
    is("b1010".U) { // BEQ
      io.flag := io.in0 === io.in1
    }
    is("b1011".U) { // BNE
      io.flag := io.in0 =/= io.in1
    }
    is("b1100".U) { // BLT
      io.flag := io.in0.asSInt() < io.in1.asSInt()
    }
    is("b1101".U) { // BLTU
      io.flag := io.in0 < io.in1
    }
    is("b1110".U) { // BGE
      io.flag := io.in0.asSInt() >= io.in1.asSInt()
    }
    is("b1111".U) { // BGEU
      io.flag := io.in0 >= io.in1
    }
  }
}