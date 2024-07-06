import Image from "next/image"

const TokCoin = ({coinSize=30} : {coinSize? : number}) => {
  return (
    <div className="border-4 rounded-full bg-black border-tiktok-cyan p-2">  
      <Image
        src="/tiktokIconWhite.svg"
        alt="Logo"
        width={coinSize}
        height={coinSize}
      />
    </div>
  )
}

const TokCoins = ({mainCoinSize = 30}) => {
  return (
    <div className="relative inline-block" style={{ width: `${mainCoinSize * 2.2}px`, height: `${mainCoinSize * 1.8}px` }}>
      
      {/* Top right coin */}
      <div className="absolute top-0 right-0 transform rotate-[5deg]" style={{ zIndex: 2 }}>
        <TokCoin coinSize={Math.floor(mainCoinSize * 0.35)} />
      </div>
      
      {/* Center coin (slightly larger to appear on top) */}
      <div className="absolute top-1/2 left-1/2 transform rotate-[-15deg] -translate-x-1/2 -translate-y-1/2" style={{ zIndex: 3 }}>
        <TokCoin coinSize={mainCoinSize} />
      </div>
    </div>
  )
}

export { TokCoin, TokCoins }