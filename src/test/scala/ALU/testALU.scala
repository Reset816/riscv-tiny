package ALU

import chisel3.Driver

object testALU extends App {
  Driver.execute(args, () => new ALU)
}
