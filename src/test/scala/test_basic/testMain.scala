package test_basic

import chisel3.stage.{ChiselGeneratorAnnotation, ChiselStage}
import firrtl.options.TargetDirAnnotation

//test:runMain test_basic.testMain
object testMain extends App {
  (new chisel3.stage.ChiselStage).execute(
    Array("-X", "verilog", "--full-stacktrace"),
    Seq(ChiselGeneratorAnnotation(() => new AND()),
      TargetDirAnnotation("generated/and"))
  )
}