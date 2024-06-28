import Image from 'next/image';
import { type TiktokCardDetails } from "@/hooks/useFetchTiktokCard";

interface TiktokCardProps extends TiktokCardDetails {
  hideDetails: boolean;
  variant: 'blue' | 'red' | 'black';
}

const TiktokCard: React.FC<TiktokCardProps> = ({ number, cardName, cvc, expiryDate, hideDetails, variant }) => {

  const cardClasses = {
    blue: 'bg-gradient-to-br from-blue-700 via-blue-900 to-blue-950',
    red: 'bg-gradient-to-br from-red-700 via-red-900 to-red-950',
    black: 'bg-gradient-to-br from-gray-700 via-gray-900 to-black',
  };

  const overlayClasses = {
    blue: 'from-blue-300 via-blue-200 to-blue-300',
    red: 'from-red-300 via-red-200 to-red-300',
    black: 'from-gray-300 via-gray-200 to-gray-300',
  };

  return (
    <div className={`relative ${cardClasses[variant]} rounded-xl p-6 w-full max-w-[350px] aspect-[1.58/1] shadow-lg text-white flex flex-col justify-between overflow-hidden`}>
      {/* Metallic overlay */}
      <div className={`absolute inset-0 bg-gradient-to-tr ${overlayClasses[variant]} opacity-10`}></div>
      
      {/* Card content */}
      <div className="relative z-10 flex flex-col h-full">
        <div className="flex justify-between items-start mb-4">
          <div className="text-sm font-semibold">TikTok Card</div>
          <div className="text-sm">{hideDetails ? '***' : cvc}</div>
        </div>
        
        <div className="flex justify-between items-center mb-4">
          
          {/* TikTok logo */}
          <div className="w-12 h-12 opacity-30">
            <Image src="/tiktokIconNoBg.svg" alt="TikTok" layout="fill" objectFit="contain" />
          </div>
        </div>
        
        <div className="text-lg font-mono tracking-widest mb-6 flex flex-wrap">
          {number.map((num, index) => (
            <span key={index} className="mr-2">{hideDetails ? '****' : num}</span>
          ))}
        </div>
        
        <div className="mt-auto flex justify-between items-end">
          <div>
            <div className="text-xs uppercase opacity-75">Card Holder</div>
            <div className="text-sm font-semibold">{hideDetails ? '****' : cardName}</div>
          </div>
          <div>
            <div className="text-xs uppercase opacity-75">Expiry</div>
            <div className="text-sm font-semibold">{hideDetails ? '**/**' : expiryDate}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TiktokCard;