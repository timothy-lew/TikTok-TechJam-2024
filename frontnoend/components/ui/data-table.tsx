"use client"
import { Input } from "@/components/ui/input"
import {
  ColumnDef,
  ColumnFiltersState,
  flexRender,
  getCoreRowModel,
  useReactTable,
  getPaginationRowModel,
  getFilteredRowModel,
  SortingState,
  getSortedRowModel,
} from "@tanstack/react-table"
import { Button } from "@/components/ui/button"
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"


import { useState } from "react"
import TikTokLoader from "../shared/TiktokLoader"

interface DataTableProps<TData, TValue> {
  columns: ColumnDef<TData, TValue>[]
  data: TData[]
}

export function DataTable<TData, TValue>({
  columns,
  data,
}: DataTableProps<TData, TValue>) {

  const [sorting, setSorting] = useState<SortingState>([]);
  const [columnFilters, setColumnFilters] = useState<ColumnFiltersState>([]);
  const table = useReactTable({
    data,
    columns,
    getPaginationRowModel: getPaginationRowModel(),
    getCoreRowModel: getCoreRowModel(),
    onSortingChange: setSorting,
    getSortedRowModel: getSortedRowModel(),
    onColumnFiltersChange: setColumnFilters,
    getFilteredRowModel: getFilteredRowModel(),
    state: {
      sorting,
      columnFilters,
    }
  });

  // SELECTOR 
  const CLEAR_FILTER_VALUE = "CLEAR_FILTER";
  const [selectorFilter, setSelectorFilter] = useState<string>("");

  const [monthFilter, setMonthFilter] = useState<string>("");
  const monthOptions = [
    "January", "February", "March", "April", "May", "June", 
    "July", "August", "September", "October", "November", "December"
  ];

  return (
    <div className="rounded-md border">
      <div className="flex_center gap-4 py-4">

        <Select
          onValueChange={(value) => {
            if (value === CLEAR_FILTER_VALUE) {
              table.getColumn('transactionType')?.setFilterValue("");
              setSelectorFilter("")
            }
            else {
              table.getColumn('transactionType')?.setFilterValue(value);
              setSelectorFilter(value)
            }
          }}
          value={selectorFilter}
        >
          <SelectTrigger className="border-slate-300 max-w-xs">
            <SelectValue placeholder="Filter Status"/>
          </SelectTrigger>

          <SelectContent>
            {["PURCHASE", "CONVERSION", "TOPUP", "WITHDRAW"].map((option, index) =>
              <SelectItem className="hover:cursor-pointer hover:bg-slate-200 " value={option} key={`${option}_${index}`}>
                <p className="capitalize">{option.toLowerCase()}</p>
              </SelectItem>
            )}

            <SelectItem value={CLEAR_FILTER_VALUE} className="text-red-500 hover:cursor-pointer hover:bg-slate-100">Clear Filter</SelectItem>

          </SelectContent>

        </Select>

        <Select
          onValueChange={(value) => {
            if (value === CLEAR_FILTER_VALUE) {
              table.getColumn('transactionDate')?.setFilterValue("");
              setMonthFilter("")
            }
            else {
              table.getColumn('transactionDate')?.setFilterValue(value);
              setMonthFilter(value)
            }
          }}
          value={monthFilter}
        >
          <SelectTrigger className="border-slate-300 max-w-xs">
            <SelectValue placeholder="Filter Month"/>
          </SelectTrigger>

          <SelectContent>
            {monthOptions.map((month, index) =>
              <SelectItem className="hover:cursor-pointer hover:bg-slate-200" value={month} key={`${month}_${index}`}>
                {month}
              </SelectItem>
            )}

            <SelectItem value={CLEAR_FILTER_VALUE} className="text-red-500 hover:cursor-pointer hover:bg-slate-100">Clear Filter</SelectItem>
          </SelectContent>
        </Select>

      </div>
      
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
                )
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
                    {flexRender(cell.column.columnDef.cell, cell.getContext())}
                  </TableCell>
                ))}
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell colSpan={columns.length} className="h-24 text-center">
                <div className="flex justify-center items-center h-full">
                  <TikTokLoader />
                </div>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
      <div className="flex items-center justify-end space-x-2 py-4">
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
  )
}
