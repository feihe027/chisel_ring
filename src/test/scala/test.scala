package counter
import chisel3._
import chiseltest._
import chisel3.util._
import org.scalatest.flatspec.AnyFlatSpec
import _root_.circt.stage.ChiselStage



class SimpleTestExpect extends AnyFlatSpec with ChiselScalatestTester {
  "DUT" should "pass" in {
    test(new UpCounter(4))
    .withAnnotations(Seq(WriteVcdAnnotation))  { dut =>
      dut.reset.poke(true.B)
      dut.clock.step()
      dut.reset.poke(false.B)
      dut.clock.step()
      dut.io.count.expect(1.U)
      println(dut.io.count.peek())
      dut.clock.step()
      dut.io.count.expect(2.U)
      println(dut.io.count.peek())
      dut.clock.step()
      dut.io.count.expect(3.U)
      println(dut.io.count.peek())
      dut.clock.step()
      dut.io.count.expect(4.U)
      println(dut.io.count.peek())
    }
  }
}
