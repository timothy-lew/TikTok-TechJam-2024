"use client";
import { getBackendUrl } from "@/lib/utils";

import * as React from "react";
import {
  ColumnDef,
  ColumnFiltersState,
  RowData,
  SortingState,
  VisibilityState,
  flexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getPaginationRowModel,
  getSortedRowModel,
  useReactTable,
} from "@tanstack/react-table";
import { ArrowUpDown, ChevronDown, MoreHorizontal } from "lucide-react";

import { Button } from "@/components/ui/button";
import { Checkbox } from "@/components/ui/checkbox";
import {
  DropdownMenu,
  DropdownMenuCheckboxItem,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { useAuth } from "@/hooks/auth-provider";

declare module "@tanstack/react-table" {
  interface TableMeta<TData extends RowData> {
    setToEdit: (toEdit: string) => void;
    deleteHandler: (id: string) => void;
  }
}

// const data: Listing[] = [{
//         "id":"667c2106fe08ec33cf32e73c",
//         "sellerProfileId":"66813c84f8bd211375579168",
//         "name":"Product 1",
//         "description":"Unisex",
//         "price":1,
//         "tokTokenPrice":11,
//         "quantity":111,
//         "imageUrl":"dummy",
//         "businessName":"Summer Wears",
//         "sellerWalletAddress":"0x90F79bf6EB2c4f870365E785982E1f101E93b906"},
//     {"id":"667c2106fe08ec33cf32e73c",
//         "sellerProfileId":"66813c84f8bd211375579168",
//         "name":"Product 2",
//         "description":"Unisex",
//         "price":2,
//         "tokTokenPrice":22,
//         "quantity":222,
//         "imageUrl":"dummy",
//         // "imageUrl":"Dummy",
//         "businessName":"Summer Wears",
//         "sellerWalletAddress":"0x90F79bf6EB2c4f870365E785982E1f101E93b906"
//     }]

export type Listing = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  tokTokenPrice: number;
  quantity: number;
  imageUrl: string | File;
  businessName: string;
  sellerWalletAddress: string;
};

export const columns: ColumnDef<Listing>[] = [
  //   {
  //     id: "select",
  //     header: ({ table }) => (
  //       <Checkbox
  //         checked={
  //           table.getIsAllPageRowsSelected() ||
  //           (table.getIsSomePageRowsSelected() && "indeterminate")
  //         }
  //         onCheckedChange={(value) => table.toggleAllPageRowsSelected(!!value)}
  //         aria-label="Select all"
  //       />
  //     ),
  //     cell: ({ row }) => (
  //       <Checkbox
  //         checked={row.getIsSelected()}
  //         onCheckedChange={(value) => row.toggleSelected(!!value)}
  //         aria-label="Select row"
  //       />
  //     ),
  //     enableSorting: false,
  //     enableHiding: false,
  //   },
  {
    accessorKey: "imageUrl",
    header: "Preview",
    cell: ({ row }) => (
      <div className="capitalize">
        {/* Title: <span text="${title}">name</span> */}
        <img
          alt="preview"
          style={{ height: 50, width: 50, objectFit: "cover" }}
          src={`data:image/png;base64, ${row.getValue("imageUrl")}`}
        />
      </div>
    ),
  },
  {
    accessorKey: "name",
    header: "Name",
    cell: ({ row }) => <div className="capitalize">{row.getValue("name")}</div>,
  },
  {
    accessorKey: "price",
    header: "Price",
    // header: ({ column }) => {
    //   return (
    //     <Button
    //       variant="ghost"
    //       onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    //     >
    //       Email
    //       <ArrowUpDown className="ml-2 h-4 w-4" />
    //     </Button>
    //   )
    // },
    cell: ({ row }) => <div className="lowercase">{row.getValue("price")}</div>,
  },
  {
    accessorKey: "tokTokenPrice",
    header: "tokToken Price",
    // header: ({ column }) => {
    //   return (
    //     <Button
    //       variant="ghost"
    //       onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    //     >
    //       Email
    //       <ArrowUpDown className="ml-2 h-4 w-4" />
    //     </Button>
    //   )
    // },
    cell: ({ row }) => (
      <div className="lowercase">{row.getValue("tokTokenPrice")}</div>
    ),
  },
  {
    accessorKey: "quantity",
    header: "Quantity",
    // header: ({ column }) => {
    //   return (
    //     <Button
    //       variant="ghost"
    //       onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
    //     >
    //       Email
    //       <ArrowUpDown className="ml-2 h-4 w-4" />
    //     </Button>
    //   )
    // },
    cell: ({ row }) => (
      <div className="lowercase">{row.getValue("quantity")}</div>
    ),
  },
  //   {
  //     accessorKey: "amount",
  //     header: () => <div className="text-right">Amount</div>,
  //     cell: ({ row }) => {
  //       const amount = parseFloat(row.getValue("amount"))

  //       // Format the amount as a dollar amount
  //       const formatted = new Intl.NumberFormat("en-US", {
  //         style: "currency",
  //         currency: "USD",
  //       }).format(amount)

  //       return <div className="text-right font-medium">{formatted}</div>
  //     },
  //   },
  {
    id: "actions",
    enableHiding: false,
    cell: ({ row, table }) => {
      //   const payment = row.original

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
              onClick={() => {
                console.log("Edit clicked");
                table.options.meta?.setToEdit(row.original.id);
              }}
            >
              Edit Listing
            </DropdownMenuItem>
            <DropdownMenuItem
              onClick={() => {
                // console.log("Delete clicked")
                table.options.meta?.deleteHandler(row.original.id);
                // fetch(`${getBackendUrl()}/api/items/${row.original.id}`, {
                //     method: 'DELETE',
                //     headers: {
                //         'Authorization': `Bearer ${table.options.meta?.accessToken}`
                //     }
                // }).then(res => {
                //     if (res.ok) {
                //         console.log("Item deleted")
                //     }
                // })
              }}
              className="text-red-500"
            >
              Delete
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem>History</DropdownMenuItem>
            {/* <DropdownMenuItem>View payment details</DropdownMenuItem> */}
          </DropdownMenuContent>
        </DropdownMenu>
      );
    },
  },
];

export interface DataTableDemoProps {
  data: Listing[];
  setToEdit: (id: string) => void;
  setIsDelete: (isDelete: "deleted" | "normal") => void;
}

export function DataTableDemo({
  data,
  setToEdit,
  setIsDelete,
}: DataTableDemoProps) {
  const [accessToken, setAccessToken] = React.useState<string>("");
  const auth = useAuth();

  React.useEffect(() => {
    const fetchAccessToken = () => {
      auth?.obtainAccessToken().then((res) => {
        setAccessToken(res || "none");
      });
    };
    fetchAccessToken();
  }, []);

  const deleteHandler = (id: string) => {
    fetch(`${getBackendUrl()}/api/items/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }).then((res) => {
      if (res.ok) {
        console.log("Item deleted");
        setIsDelete("deleted");
      }
    });
  };

  const [sorting, setSorting] = React.useState<SortingState>([]);
  const [columnFilters, setColumnFilters] = React.useState<ColumnFiltersState>(
    []
  );
  const [columnVisibility, setColumnVisibility] =
    React.useState<VisibilityState>({});
  const [rowSelection, setRowSelection] = React.useState({});

  const table = useReactTable<Listing>({
    data,
    columns,
    onSortingChange: setSorting,
    onColumnFiltersChange: setColumnFilters,
    getCoreRowModel: getCoreRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
    getSortedRowModel: getSortedRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
    onColumnVisibilityChange: setColumnVisibility,
    onRowSelectionChange: setRowSelection,
    meta: {
      setToEdit,
      deleteHandler,
    },
    state: {
      sorting,
      columnFilters,
      columnVisibility,
      rowSelection,
    },
  });

  return (
    <div className="w-full">
      {/* <div className="flex items-center py-4">
        <Input
          placeholder="Filter emails..."
          value={(table.getColumn("email")?.getFilterValue() as string) ?? ""}
          onChange={(event) =>
            table.getColumn("email")?.setFilterValue(event.target.value)
          }
          className="max-w-sm"
        />
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="outline" className="ml-auto">
              Columns <ChevronDown className="ml-2 h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            {table
              .getAllColumns()
            //   .filter((column) => column.getCanHide())
              .map((column) => {
                return (
                  <DropdownMenuCheckboxItem
                    key={column.id}
                    className="capitalize"
                    checked={column.getIsVisible()}
                    onCheckedChange={(value) =>
                      column.toggleVisibility(!!value)
                    }
                  >
                    {column.id}
                  </DropdownMenuCheckboxItem>
                )
              })}
          </DropdownMenuContent>
        </DropdownMenu>
      </div> */}
      <div className="rounded-md border">
        <Table>
          <TableHeader>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => {
                  return (
                    <TableHead key={header.id}>
                      {header.isPlaceholder
                        ? null
                        : flexRender(
                            header.column.columnDef.header,
                            header.getContext()
                          )}
                    </TableHead>
                  );
                })}
              </TableRow>
            ))}
          </TableHeader>
          <TableBody>
            {table.getRowModel().rows?.length ? (
              table.getRowModel().rows.map((row) => (
                <TableRow
                  key={row.id}
                  data-state={row.getIsSelected() && "selected"}
                >
                  {row.getVisibleCells().map((cell) => (
                    <TableCell key={cell.id}>
                      {flexRender(
                        cell.column.columnDef.cell,
                        cell.getContext()
                      )}
                    </TableCell>
                  ))}
                </TableRow>
              ))
            ) : (
              <TableRow>
                <TableCell
                  colSpan={columns.length}
                  className="h-24 text-center"
                >
                  No results.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
      <div className="flex items-center justify-end space-x-2 py-4">
        {/* <div className="flex-1 text-sm text-muted-foreground">
          {table.getFilteredSelectedRowModel().rows.length} of{" "}
          {table.getFilteredRowModel().rows.length} row(s) selected.
        </div> */}
        <div className="space-x-2">
          <Button
            variant="outline"
            size="sm"
            onClick={() => table.previousPage()}
            disabled={!table.getCanPreviousPage()}
          >
            Previous
          </Button>
          <Button
            variant="outline"
            size="sm"
            onClick={() => table.nextPage()}
            disabled={!table.getCanNextPage()}
          >
            Next
          </Button>
        </div>
      </div>
    </div>
  );
}
