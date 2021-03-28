package ALU

import chisel3.stage.{ChiselGeneratorAnnotation, ChiselStage}
import firrtl.options.TargetDirAnnotation

//test:runMain ALU.testALU
object testALU extends App {
  (new chisel3.stage.ChiselStage).execute(
    Array("-X", "verilog", "--full-stacktrace"),
    Seq(ChiselGeneratorAnnotation(() => new ALU()),
      TargetDirAnnotation("generated/ALU"))
  )
}