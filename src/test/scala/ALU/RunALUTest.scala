package ALU

//test:runMain ALU.RunALUTest -td ./generated/ALUTest --is-verbose
object RunALUTest extends App {
  chisel3.iotesters.Driver.execute(args, () => new ALU)(c => new ALUTest(c))
}