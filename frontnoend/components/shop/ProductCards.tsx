import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "../ui/card";
import { Button } from "../ui/button";
import Link from "next/link";
import Image from "next/image";
import { Product } from "@/types/ShopTypes";

export function ProductCard({ product }: { product: Product }) {
  const {
    id,
    name,
    description,
    price,
    quantity,
    imageUrl,
    discountedTokTokenPrice,
    tokTokenPrice,
    discountRate,
  } = product;
  return (
    <Card className="flex overflow-hidden flex-col">
      <div className="relative w-full h-auto aspect-video">
        {imageUrl && (
          <Image
            src={`data:image/png;base64,${imageUrl}`}
            fill
            alt={name}
            className="object-contain w-full h-full"
          />
        )}
      </div>
      <CardHeader>
        <CardTitle>{name}</CardTitle>
        <CardDescription>
          <h3 className="text-lg font-bold  text-red-500">
            ${price} or{" "}
            {discountedTokTokenPrice ? (
              <>
                {Math.floor(discountedTokTokenPrice)} Tok Coins{" "}
                <s className="text-xs text-gray-700">
                  {tokTokenPrice} Tok Coins
                </s>
              </>
            ) : (
              `${tokTokenPrice} Tok Coins`
            )}
          </h3>
        </CardDescription>
        {discountRate ? (
          <CardDescription>
            <span className="inline-block bg-yellow-100 text-yellow-700 text-xs font-semibold px-2 py-1 rounded-full border border-yellow-500">
              Save {discountRate} with Tok Coin!
            </span>
          </CardDescription>
        ) : (
          ""
        )}
      </CardHeader>
      <CardContent className="flex-grow">
        Sold by{" "}
        <Link
          className="underline"
          href={`/shop/seller/${product.sellerProfileId}`}
        >
          {product.businessName}
        </Link>
      </CardContent>

      <CardFooter>
        <Button
          asChild
          size="lg"
          className="w-full bg-white text-black border border-gray-400 hover:bg-black hover:text-white focus:bg-black focus:text-white"
        >
          <Link href={`/shop/${id}/`}>Purchase</Link>
        </Button>
      </CardFooter>
    </Card>
  );
}

export function ProductCardSkeleton() {
  return (
    <Card className="overflow-hidden flex flex-col animate-pulse">
      <div className="w-full aspect-video bg-gray-300" />
      <CardHeader>
        <CardTitle>
          <div className="w-3/4 h-6 rounded-full bg-gray-300" />
        </CardTitle>
        <CardDescription>
          <div className="w-1/2 h-4 rounded-full bg-gray-300" />
        </CardDescription>
      </CardHeader>
      <CardContent className="space-y-2">
        <div className="w-full h-4 rounded-full bg-gray-300" />
        <div className="w-full h-4 rounded-full bg-gray-300" />
        <div className="w-3/4 h-4 rounded-full bg-gray-300" />
      </CardContent>
      <CardFooter>
        <Button className="w-full" disabled size="lg"></Button>
      </CardFooter>
    </Card>
  );
}

export function ProductDetailsSkeleton() {
  return (
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="overflow-hidden flex flex-col animate-pulse">
        <div className="relative w-full aspect-video bg-gray-300" />
        <CardHeader>
          <CardTitle>
            <div className="w-3/4 h-6 rounded-full bg-gray-300" />
          </CardTitle>
          <CardDescription>
            <div className="w-1/2 h-4 rounded-full bg-gray-300" />
          </CardDescription>
          <CardDescription>
            <div className="w-1/3 h-4 rounded-full bg-gray-300" />
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-2 flex-grow">
          <div className="w-full h-4 rounded-full bg-gray-300" />
          <div className="w-full h-4 rounded-full bg-gray-300" />
          <div className="w-3/4 h-4 rounded-full bg-gray-300" />
          <div className="w-1/4 h-4 rounded-full bg-gray-300" />
        </CardContent>
        <CardFooter>
          <Button
            className="w-full bg-gray-300 text-transparent"
            disabled
            size="lg"
          >
            Buy Now
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}

export function ProductCardDetails({
  product,
  sellerBusinessName,
  openModal,
}: {
  product: Product;
  sellerBusinessName: string | null;
  openModal: () => void;
}) {
  const {
    id,
    name,
    description,
    price,
    quantity,
    imageUrl,
    discountedTokTokenPrice,
    tokTokenPrice,
    discountRate,
  } = product;
  return (
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="flex overflow-hidden flex-col">
        <div className="relative w-full h-auto aspect-video">
          {imageUrl && (
            <Image
              src={`data:image/png;base64,${imageUrl}`}
              fill
              alt={name}
              className="object-contain w-full h-full"
            />
          )}
        </div>
        <CardHeader>
          <CardTitle>{name}</CardTitle>
          <CardDescription>
            <h3 className="text-lg font-bold  text-red-500">
              ${price} or{" "}
              {discountedTokTokenPrice ? (
                <>
                  {Math.floor(discountedTokTokenPrice)} Tok Coins{" "}
                  <s className="text-xs text-gray-700">
                    {tokTokenPrice} Tok Coins
                  </s>
                </>
              ) : (
                `${tokTokenPrice} Tok Coins`
              )}
            </h3>
          </CardDescription>
          {discountRate ? (
          <CardDescription>
            <span className="inline-block bg-yellow-100 text-yellow-700 text-xs font-semibold px-2 py-1 rounded-full border border-yellow-500">
              Save {discountRate} with Tok Coin!
            </span>
          </CardDescription>
        ) : (
          ""
        )}
          <CardDescription>
            Sold by{" "}
            <Link
              className="underline"
              href={`/shop/seller/${product.sellerProfileId}`}
            >
              {sellerBusinessName}
            </Link>
          </CardDescription>
        </CardHeader>

        <CardContent className="flex-grow">
          <h3 className="line-clamp-4">{description}</h3>
          <p>Quantity available: {quantity}</p>
        </CardContent>
        <CardFooter>
          <Button
            onClick={openModal}
            size="lg"
            className="w-full bg-red-500 hover:bg-red-600"
          >
            Buy Now
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}
