"use client";

import { ColumnDef } from "@tanstack/react-table";
import { ArrowUpDown, MoreHorizontal } from "lucide-react";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuLabel, DropdownMenuSeparator, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";

export type Item = {
  id: number
  name: string
  unitprice: number
  quantity: number
  total: number
}

export type Order = {
  id: number
  orderdate: Date
  items: Item[]
  total: number
  downpayment: number
  remaining: number
  status: "Placed" | "Preparing" | "Shipped" | "Delivered" | "Completed"
  tracking: string
  bank: "Down Payment Requested" | "Down Payment Received" | "Full Payment Requested" | "Full Payment Received"
  customer: string
}

export const columns: ColumnDef<Order>[] = [
  {
    accessorKey: "id",
    header: "Order ID",
    cell: ({ row }) => {
      return <p className="text-right">{row.getValue("id")}</p>
    },
  },
//   { accessorKey: "orderdate", header: "Date Placed", cell: ({row}) => {
//     return <p>{row.getValue("orderdate").toISOString().split('T')[0]}</p>
//   },
//  },
  { accessorKey: "customer", header: "Customer" },
  {
    accessorKey: "total",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Total Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("total"))
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return <div className="font-medium">{formatted}</div>
    },
  },
  { accessorKey: "downpayment", header: "Down Payment (%)" },
  {
    accessorKey: "remaining",
    header: ({ column }) => {
      return (
        <Button
          variant="ghost"
          onClick={() => column.toggleSorting(column.getIsSorted() === "asc")}
        >
          Remaining Amount
          <ArrowUpDown className="w-4 h-4 ml-2" />
        </Button>
      )
    },
    cell: ({ row }) => {
      const amount = parseFloat(row.getValue("remaining"))
      const formatted = new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "MYR",
      }).format(amount)

      return <div className="font-bold text-destructive">{formatted}</div>
    },
  },
  {
    accessorKey: "status",
    header: "Order Status",
    cell: ({ row }) => {
      return (
        <Badge
          variant={
            row.getValue("status") == "Completed" ? "success" : "default"
          }
        >
          {row.getValue("status")}
        </Badge>
      )
    },
  },
  { accessorKey: "tracking", header: "Tracking Number" },
  { accessorKey: "bank", header: "Bank Status" },
  {
    id: "actions",
    cell: ({ row }) => {
      const order = row.original

      return (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" className="w-8 h-8 p-0">
              <span className="sr-only">Open menu</span>
              <MoreHorizontal className="w-4 h-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuLabel>Actions</DropdownMenuLabel>
            {/* <DropdownMenuItem
              onClick={() => navigator.clipboard.writeText(payment.id)}
            >
              Copy payment ID
            </DropdownMenuItem> */}
            <DropdownMenuSeparator />
            <DropdownMenuItem>View order details</DropdownMenuItem>
            <DropdownMenuItem>Send invoice</DropdownMenuItem>
            <DropdownMenuItem onClick={() => navigator.clipboard.writeText(order.tracking)}>Copy tracking number</DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      )
    },
  },
]