
import { ColumnDef } from "@tanstack/react-table"
import { format, parseISO } from 'date-fns';
import { ArrowUpDown, MoreHorizontal } from "lucide-react"
import { Button } from "@/components/ui/button"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Dialog,
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { type Transaction } from '@/hooks/useFetchTransactions';

export const columns: ColumnDef<Transaction>[] = [
  {
    accessorKey: "transactionType",
    header: "Type",
  },
  {
    accessorKey: "transactionDate",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Date
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      );
    },
    cell: ({ row }) => {
      const dateString: string = row.getValue("transactionDate");
      const date = parseISO(dateString);
      const formattedDate = format(date, 'dd/MM/yy');
      return <div>{formattedDate}</div>;
    },
    filterFn: (row, columnId, filterValue) => {
      if (filterValue === "") return true; 
      const dateString: string = row.getValue(columnId);
      const date = parseISO(dateString);
      const month = format(date, 'MMMM');
      return month.toLowerCase() === filterValue.toLowerCase();
    },
  },
  {
    accessorKey: "amount",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Amount
          <ArrowUpDown className="ml-2 h-4 w-4" />
        </Button>
      );
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("amount"));
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "SGD",
      }).format(amount);

      return <div className="text-left font-medium">{formatted}</div>;
    },
  },
  {
    id: "actions",
    cell: ({ row }) => {
      const transaction = row.original;

      const isPurchase = transaction.transactionType === "PURCHASE";

      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="h-8 w-8 p-0">
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            <DropdownMenuItem
              onClick={() => navigator.clipboard.writeText(transaction.id)}
              className="hover:bg-slate-200 cursor-pointer"
            >
              Copy transaction ID
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <Dialog>
              <DialogTrigger className="hover:bg-accent hover:text-white w-full relative flex cursor-default select-none items-center rounded-sm px-2 py-1.5 text-sm outline-none transition-colors focus:bg-accent focus:text-accent-foreground data-[disabled]:pointer-events-none data-[disabled]:opacity-50">
                View transaction details
              </DialogTrigger>
              <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                  <DialogTitle>Edit profile</DialogTitle>
                  <DialogDescription>
                    {transaction.desc}
                  </DialogDescription>
                </DialogHeader>
              </DialogContent>
            </Dialog>
            {isPurchase && <DropdownMenuItem>View seller shop</DropdownMenuItem>}
            <DropdownMenuItem className="text-red-500 hover:bg-slate-200 ">Report unauthorised transaction</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      );
    },
  },
];