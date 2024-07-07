import { useFetchShopItem } from '@/hooks/useFetchShopItem';
import Image from 'next/image';
import { ProductCard } from '../shop/ProductCards';
import { Transaction } from '@/hooks/useFetchTransactions';
import { format, parseISO } from 'date-fns';

const PurchaseTransactionView = ({ itemId, transaction }: { itemId: string | undefined, transaction: Transaction }) => {
	
	const { shopItem, loading, error } = useFetchShopItem(itemId);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;
	
  if (!shopItem) return <div>Item No Longer Available</div>;
	
	const dateString = transaction.transactionDate;
	const date = parseISO(dateString);
	const formattedDate = format(date, 'dd/MM/yy');
	return (
    <div className="w-full flex_col_center gap-4">
			<p>You purchased {transaction.purchaseDetails?.quantity} {shopItem.name} for a total of {transaction.purchaseDetails?.purchaseType==="CASH" && 'SGD'}{transaction.amount}{transaction.purchaseDetails?.purchaseType==="TOK_TOKEN" && 'Tok Coins'} on {formattedDate}</p>
			{/* To find out why cannot load image */}
			<div className="relative w-full h-auto aspect-video">
        {shopItem.imageUrl && (
          <Image
            src={`data:image/png;base64,${shopItem.imageUrl}`}
            fill
            alt={shopItem.name}
            className="rounded-xl object-contain w-full h-full"
          />
        )}
      </div>


    </div>
  );
};

export default PurchaseTransactionView;