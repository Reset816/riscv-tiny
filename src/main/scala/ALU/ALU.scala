package ALU

import chisel3.util.switch
import chisel3._
import chisel3.util.is

class ALU extends RawModule {
  val io = IO(new Bundle {
    val op = Input(UInt(4.W)) // op from ALU control
    val in0 = Input(UInt(32.W))
    val in1 = Input(UInt(32.W))
    //val in2 = Input(UInt(32.W)) // rd
    var out = Output(UInt(32.W))
    var flag = Output(Bool())
  })
  //  def opmatch() = {
  //    io.out = io.op.to match {
  //      //case 1.U(4.W) => io.in0 + io.in1 //ADD
  //      case UInt(1,4) => io.in0 + io.in1 //ADD
  ////      case  => io.in0 - io.in1 //SUB
  ////      case 3 => "many"
  //    }
  //  }
  //  def opma(x:Uint) =x match{
  //    case 1.U => 1
  //  }

  //  switch(io.op) {
  //    is (0.U(4.W)) {io.out := io.in0 + io.in1} //ADD
  //    is (1.U(4.W)) {io.out := io.in0 - io.in1} //SUB
  //  }
  io.out := 0.U
  io.flag := false.B


  //  def ShiftRight(mod: Boolean): Any = {
  //    var shift = io.in0.asUInt().do_%("32".U) // %=
  //    if (shift == "0".U)
  //      return Unit
  //
  //    io.out := io.in0
  //    if (mod) { // 逻辑右移
  //      io.out >> 1
  //      io.out(31) := false.B // 最高位置0
  //    }
  //
  //
  //    if (shift.do_>=("20".U).litToBoolean) {
  //      shift.do_-("20".U) // -=
  //      io.out >> 20
  //    }
  //    io.out >> shift
  //
  //  }
  def ShiftLift(mod: Boolean): Any = {
    var shift = io.in0.asUInt().do_%("32".U) // %=
    if (shift == "0".U)
      return Unit

    io.out := io.in0
    if (mod) { // 逻辑右移
      io.out >> 1
      io.out(31) := false.B // 最高位置0
    }


    if (shift.do_>=("20".U).litToBoolean) {
      shift.do_-("20".U) // -=
      io.out >> 20
    }
    io.out >> shift

  }

  val shift: UInt = io.in1(4, 0).asUInt()

  switch(io.op) {
    is("b0000".U) { // ADD
      io.out := io.in0 + io.in1
    }
    is("b0001".U) { // SUB
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
      io.flag := io.in0.asSInt() < io.in1.asSInt()
      if (io.flag == true.B) {
        io.out := 1.U(32.W)
      } else {
        io.out := 0.U(32.W)
      }
    }
    is("b1001".U) { // SLTU
      io.flag := io.in0 < io.in1
      if (io.flag == true.B) {
        io.out := 1.U(32.W)
      } else {
        io.out := 0.U(32.W)
      }
    }
    is("b1010".U) { // BEQ
      if (io.in0 == io.in1) {
        io.flag := true.B
      }
    }
    is("b1011".U) { // BNE
      if (io.in0 != io.in1) {
        io.flag := true.B
      }
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