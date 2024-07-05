import Image from 'next/image';
import { type TiktokCardDetails } from "@/hooks/useFetchTiktokCard";

interface TiktokCardProps extends TiktokCardDetails {
  hideDetails: boolean;
  frozen: boolean;
  noDetails: boolean;
  variant: 'blue' | 'red' | 'black';
}

const TiktokCard: React.FC<TiktokCardProps> = ({ number, cardName, cvc, expiryDate, hideDetails, frozen, noDetails, variant }) => {

  const cardClasses = {
    blue: 'bg-gradient-to-br from-cyan-200 via-cyan-400 to-blue-500',
    red: 'bg-gradient-to-br from-rose-600 via-red-800 to-red-950',
    black: 'bg-gradient-to-br from-gray-600 via-gray-800 to-black',
  };

  const overlayClasses = {
    blue: 'from-white via-cyan-100 to-cyan-200',
    red: 'from-rose-200 via-rose-300 to-red-400',
    black: 'from-neutral-300 via-neutral-400 to-neutral-500',
  };

  const frozenClasses = 'bg-gradient-to-br from-slate-300 via-slate-600 to-slate-300';
  const frozenOverlay = 'from-slate-300 via-slate-500 to-slate-400';

  return (
    <div className={`relative ${frozen ? frozenClasses : cardClasses[variant]} rounded-xl p-6 w-full max-w-[350px] aspect-[1.58/1] shadow-lg text-white flex flex-col justify-between overflow-hidden`}>
      {/* Metallic overlay */}
      <div className={`absolute inset-0 bg-gradient-to-tr ${frozen ? frozenOverlay : overlayClasses[variant]} ${frozen ? 'opacity-30' : 'opacity-10'}`}></div>
      
      {/* Frost effect for frozen card */}
      {frozen && (
        <div className="absolute inset-0 bg-white opacity-20 backdrop-filter backdrop-blur-sm"></div>
      )}
      
      {/* Card content */}
      
      <div className="relative z-10 flex flex-col h-full">
        <div className="flex justify-between items-start mb-4">
          <div className="text-sm font-semibold">TikTok <span className="capitalize">{variant}</span></div>
          {!noDetails && <div className="text-sm">{frozen ? '***' : (hideDetails ? '***' : cvc)}</div>}
        </div>
        
        <div className="flex justify-between items-center mb-4">
          {/* TikTok logo */}
          <div className="w-12 h-12 opacity-30">
            {frozen ? 
            <Image src="/icons/freeze.svg" alt="frozen" layout="fill" objectFit="contain" />
            :
            <Image src="/tiktokIconNoBg.svg" alt="TikTok" layout="fill" objectFit="contain" />}
          </div>
        </div>
        
        {!frozen && !noDetails && <div className="text-lg font-mono tracking-widest mb-6 flex flex-wrap">
          {
            number.map((num, index) => (
              <span key={index} className="mr-2">{hideDetails ? '****' : num}</span>
            ))
          }
        </div>}
        
        {!noDetails && <div className="mt-auto flex justify-between items-end">
          <div>
            <div className="text-xs uppercase opacity-75">Card Holder</div>
            <div className="text-sm font-semibold">{frozen ? '****' : (hideDetails ? '****' : cardName)}</div>
          </div>
          <div>
            <div className="text-xs uppercase opacity-75">Expiry</div>
            <div className="text-sm font-semibold">{frozen ? '**/**' : (hideDetails ? '**/**' : expiryDate)}</div>
          </div>
        </div>}
      </div>
    </div>
  );
}


const TiktokCardBenefits = ({desc} : {desc: string}) => {
  return (
    <div className="flex_center gap-2">
      <Image src='/icons/tick.svg' height={13} width={13} alt="tick" />
      <p>{desc}</p>
    </div>
  )
}

export { TiktokCard, TiktokCardBenefits}