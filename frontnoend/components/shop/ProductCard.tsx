import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "../ui/card"
import { Button } from "../ui/button"
import Link from "next/link"
import Image from "next/image"

type ProductCardProps = {
  id: string
  name: string
  priceInCents: number
  description: string
  imagePath: string
}

export function ProductCard({
  id,
  name,
  priceInCents,
  description,
  imagePath,
}: ProductCardProps) {
  return (
    <Card className="flex overflow-hidden flex-col">
      <div className="relative w-full h-auto aspect-video">
        {imagePath && <Image src={`data:image/png;base64,${imagePath}`} fill alt={name} className="object-contain w-full h-full" />}

      </div>
      <CardHeader>
        <CardTitle>{name}</CardTitle>
        <CardDescription>${priceInCents} or {Math.round(priceInCents*100)} TikTok Coins</CardDescription>
      </CardHeader>
      <CardContent className="flex-grow">
        <p className="line-clamp-4">{description}</p>
      </CardContent>
      <CardFooter>
      <Button asChild size="lg" className="w-full bg-white text-black border border-gray-400 hover:bg-black hover:text-white focus:bg-black focus:text-white">
          <Link href={`/shop/${id}/`}>Purchase</Link>
        </Button>
      </CardFooter>
    </Card>
  )
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
  )
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
          <Button className="w-full bg-gray-300 text-transparent" disabled size="lg">
            Buy Now
          </Button>
        </CardFooter>
      </Card>
    </div>
  )
}