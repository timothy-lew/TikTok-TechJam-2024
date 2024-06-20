import { type TiktokCardDetails } from "@/hooks/useFetchTiktokCard";

interface TiktokCardProps extends TiktokCardDetails {
  hideDetails: boolean;
}


const TiktokCard: React.FC<TiktokCardProps> = ({ number, cardName, cvc, expiryDate, hideDetails }) => {


  return (
    <div className="bg-gradient-to-r from-blue-400 to-blue-600 rounded-lg p-6 h-[180px] w-[280px] xl:h-[200px] xl:w-[350px] shadow-lg text-white flex flex-col justify-between">
      <div className="flex justify-between">
        <div className="text-sm">Tiktok Card</div>
        <div className="text-sm">{hideDetails ? '***' : cvc}</div>
      </div>
      <div className="text-xl font-mono tracking-widest mt-4">
        {number.map((num, index) => (
          <span key={index}>{hideDetails ? '****' : num} </span>
        ))}
      </div>
      <div className="flex justify-between items-end mt-4">
        <div>
          <div className="text-xs uppercase">Card Holder</div>
          <div className="text-sm">{hideDetails ? '****' : cardName}</div>
        </div>
        <div>
          <div className="text-xs uppercase">Expiry</div>
          <div className="text-sm">{hideDetails ? '**/**' : expiryDate}</div>
        </div>
      </div>
    </div>
  );
}

export default TiktokCard;
