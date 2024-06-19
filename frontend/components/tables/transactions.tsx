
import { ColumnDef } from "@tanstack/react-table"
import { ArrowUpDown, MoreHorizontal } from "lucide-react"
import { Button } from "@/components/ui/button"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

export type Transaction = {
  id: string
  transactionType: "shopping" | "transfer"
  amount: number
  receiver: string
  type: "incoming" | "outgoing"
  status: "pending" | "processing" | "success" | "failed"
}

export const columns: ColumnDef<Transaction>[] = [
  {
    accessorKey: "transactionType",
    header: "Type",
  },
  {
    accessorKey: "receiver",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          To
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      )
    },
  },
  {
    accessorKey: "amount",
    header: () => <div className="text-right">Amount</div>,
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("amount"))
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(amount)
 
      return <div className="text-right font-medium">{formatted}</div>
    },
  },
  {
    accessorKey: "status",
    header: "Status",
  },
  {
    id: "actions",
    cell: ({ row }) => {
      const payment = row.original
 
      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuItem
              onClick={() => navigator.clipboard.writeText(payment.id)}
            >
              Copy payment ID
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem>View customer</DropdownMenuItem>
            <DropdownMenuItem>View payment details</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      )
    },
  },
]


export const transactionData : Transaction[] = [
  {
    id: "728ed52f",
    amount: 100,
    type: "incoming",
    receiver: "@tiktokshop1",
    transactionType: "shopping",
    status: "pending",
  },
  {
    id: "a4f8b2d1",
    amount: 250,
    type: "incoming",
    receiver: "@tiktokshop2",
    transactionType: "shopping",
    status: "success",
  },
  {
    id: "c4d3a8b9",
    amount: 75,
    type: "incoming",
    receiver: "@tiktokshop3",
    transactionType: "shopping",
    status: "success",
  },
  {
    id: "d8f2a9c4",
    amount: 150,
    type: "incoming",
    receiver: "@tiktokshop4",
    transactionType: "shopping",
    status: "failed",
  },
  {
    id: "e9c2d4f3",
    amount: 300,
    type: "incoming",
    receiver: "@tiktokshop5",
    transactionType: "shopping",
    status: "pending",
  },
  {
    id: "f5b7e6a2",
    amount: 50,
    type: "incoming",
    receiver: "@tiktokshop3",
    transactionType: "shopping",
    status: "success",
  },
  {
    id: "g3d8f1b7",
    amount: 120,
    type: "incoming",
    receiver: "@johnloh",
    transactionType: "transfer",
    status: "processing",
  },
  {
    id: "h6e7c3a5",
    amount: 200,
    type: "incoming",
    receiver: "@evelyngoh",
    transactionType: "transfer",
    status: "failed",
  },
  {
    id: "i7b9f4c2",
    amount: 90,
    type: "outgoing",
    receiver: "@alex54",
    transactionType: "transfer",
    status: "success",
  },
  {
    id: "j8a1d2e3",
    amount: 60,
    type: "incoming",
    receiver: "@harryCo",
    transactionType: "transfer",
    status: "pending",
  },
];
