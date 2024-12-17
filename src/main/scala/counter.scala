package counter  
import chisel3._  
import chisel3.util._  
import chisel3.stage.ChiselStage  
import chisel3.experimental.FixedPoint  

// 为不同类型定义 Ring trait  
trait Ring[T <: Data] {  
  def times(x: T, y: T): T  
  def plus(x: T, y: T): T  
}  

// 使用伴生对象来定义 Ring 实例
object Ring {
  // 为 UInt 提供 Ring 实例  
  implicit val uintRing: Ring[UInt] = new Ring[UInt] {  
    def times(x: UInt, y: UInt): UInt = x * y  
    def plus(x: UInt, y: UInt): UInt = x + y  
  }  

  // 为 SInt 提供 Ring 实例  
  implicit val sintRing: Ring[SInt] = new Ring[SInt] {  
    def times(x: SInt, y: SInt): SInt = x * y  
    def plus(x: SInt, y: SInt): SInt = x + y  
  }  

  // 为 FixedPoint 提供 Ring 实例  
  implicit val fixedPointRing: Ring[FixedPoint] = new Ring[FixedPoint] {  
    def times(x: FixedPoint, y: FixedPoint): FixedPoint = x * y  
    def plus(x: FixedPoint, y: FixedPoint): FixedPoint = x + y  
  }  
}

class Mac[T <: Data : Ring](genIn: T, genOut: T) extends Module {  
    val io = IO(new Bundle {  
        val a = Input(genIn)  
        val b = Input(genIn)  
        val c = Input(genIn)  
        val out = Output(genOut)  
    })  
    
    // 使用隐式 Ring 实例的方法  
    val ring = implicitly[Ring[T]]  
    io.out := ring.plus(ring.times(io.a, io.b), io.c)  
}  

object Main {  
  def main(args: Array[String]): Unit = {  
    // 使用 ChiselStage.emitVerilog() 生成 Verilog  
    println(ChiselStage.emitVerilog(new Mac(UInt(4.W), UInt(6.W))))  
    println(ChiselStage.emitVerilog(new Mac(SInt(4.W), SInt(6.W))))  
    println(ChiselStage.emitVerilog(new Mac(FixedPoint(4.W, 3.BP), FixedPoint(6.W, 4.BP))))  
  }  
}
