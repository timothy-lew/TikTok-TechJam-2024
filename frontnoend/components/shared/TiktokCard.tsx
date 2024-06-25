import { type TiktokCardDetails } from "@/hooks/useFetchTiktokCard";

interface TiktokCardProps extends TiktokCardDetails {
  hideDetails: boolean;
}

const TiktokCard: React.FC<TiktokCardProps> = ({ number, cardName, cvc, expiryDate, hideDetails }) => {
  return (
    <div className="bg-gradient-to-r from-blue-400 to-blue-600 rounded-lg p-4 md:p-6 w-full max-w-[350px] aspect-[1.58/1] shadow-lg text-white flex flex-col justify-between">
      <div className="flex justify-between">
        <div className="text-xs sm:text-sm">Tiktok Card</div>
        <div className="text-xs sm:text-sm">{hideDetails ? '***' : cvc}</div>
      </div>
      <div className="text-base sm:text-xl font-mono tracking-widest mt-2 sm:mt-4 flex flex-wrap justify-center sm:justify-start">
        {number.map((num, index) => (
          <span key={index} className="mx-1 sm:mx-0">{hideDetails ? '****' : num} </span>
        ))}
      </div>
      <div className="flex justify-between items-end mt-2 sm:mt-4">
        <div>
          <div className="text-xs uppercase">Card Holder</div>
          <div className="text-xs sm:text-sm">{hideDetails ? '****' : cardName}</div>
        </div>
        <div>
          <div className="text-xs uppercase">Expiry</div>
          <div className="text-xs sm:text-sm">{hideDetails ? '**/**' : expiryDate}</div>
        </div>
      </div>
    </div>
  );
}

export default TiktokCard;