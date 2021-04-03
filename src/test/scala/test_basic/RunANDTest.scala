package test_basic

//test:runMain test_basic.RunANDTest -td ./generated/ANDTest --is-verbose
object RunANDTest extends App {
  chisel3.iotesters.Driver.execute(args, () => new AND)(c => new ANDTest(c))
}