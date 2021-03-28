package test_basic

import chisel3.Driver

object testMain extends App {
  Driver.execute(args, () => new AND)
}
