"use client"

import "@/styles/globals.css"
import { Metadata } from "next"
import ChatBot from "react-simple-chatbot"
import { ThemeProvider as ThemeProvider2 } from "styled-components"

import { siteConfig } from "@/config/site"
import { fontSans } from "@/lib/fonts"
import { cn } from "@/lib/utils"
import { SiteHeader } from "@/components/site-header"
import { TailwindIndicator } from "@/components/tailwind-indicator"
import { ThemeProvider } from "@/components/theme-provider"

export const metadata: Metadata = {
  title: {
    default: siteConfig.name,
    template: `%s - ${siteConfig.name}`,
  },
  description: siteConfig.description,
  themeColor: [
    { media: "(prefers-color-scheme: light)", color: "white" },
    { media: "(prefers-color-scheme: dark)", color: "black" },
  ],
  icons: {
    icon: "/favicon.ico",
    shortcut: "/favicon-16x16.png",
    apple: "/apple-touch-icon.png",
  },
}

const theme = {
  // background: "#0f172a",
  background: "#f5f8fb",
  headerBgColor: "#0f172a",
  headerFontColor: "#fff",
  headerFontSize: "15px",
  botBubbleColor: "#0f172a",
  botFontColor: "#fff",
  userBubbleColor: "#fff",
  userFontColor: "#4a4a4a",
}

const steps = [
  {
    id: "1",
    message: "Welcome to SuppyControl+! How may I help you?",
    trigger: "2",
  },
  {
    id: "2",
    options: [
      {
        value: "Hi! I'm looking for supply chain financing options.",
        label: "Hi! I'm looking for supply chain financing options.",
        trigger: "3",
      },
    ],
  },
  {
    id: "3",
    message:
      "Hello! I'm here to assist you. Could you please provide me with your revenue goal and the time period you are targeting?",
    trigger: "4",
  },
  {
    id: "4",
    options: [
      {
        label: "My revenue goal is RM100,000 within the next 6 months.",
        value: "My revenue goal is RM100,000 within the next 6 months.",
        trigger: "5",
      },
    ],
  },
  {
    id: "5",
    message:
      "Great! Based on your revenue goal, I recommend exploring the following supply chain financing options: Invoice Factoring, Purchase Order Financing, Supply Chain Loans or Trade Finance",
    trigger: "6",
  },
  {
    id: "6",
    options: [
      {
        label:
          "Thanks for the suggestions! Can you provide more information on invoice factoring?",
        value:
          "Thanks for the suggestions! Can you provide more information on invoice factoring?",
        trigger: "7",
      },
    ],
  },
  {
    id: "7",
    message:
      "Certainly! Invoice factoring is a financing solution where you sell your accounts receivable to a factoring company at a discounted rate. In return, you receive immediate cash, which can help you bridge the cash flow gap caused by pending customer payments. This option can provide quick access to funds without adding debt to your balance sheet.",
    trigger: "8",
  },
  {
    id: "8",
    options: [
      {
        label: "How can I get started with invoice factoring?",
        value: "How can I get started with invoice factoring?",
        trigger: "9",
      },
    ],
  },
  {
    id: "9",
    message:
      "To get started with invoice factoring, you'll need to find a reputable factoring company that suits your business needs. They will assess your invoices and determine the amount they are willing to advance to you. You can then submit your invoices to the factoring company, and upon verification, they will provide you with the agreed-upon funding, usually within a few business days.",
    trigger: "10",
  },
  {
    id: "10",
    options: [
      {
        label:
          "Thank you for the information! I'll explore these options further.",
        value:
          "Thank you for the information! I'll explore these options further.",
        trigger: "11",
      },
    ],
  },
  {
    id: "11",
    message:
      "You're welcome! If you have any more questions or need assistance in the future, feel free to reach out. Good luck with your supply chain financing journey!",
    end: true,
  },
]

interface RootLayoutProps {
  children: React.ReactNode
}

export default function RootLayout({ children }: RootLayoutProps) {
  return (
    <>
      <html lang="en" suppressHydrationWarning>
        <head />
        <body
          className={cn(
            "bg-background min-h-screen font-sans antialiased",
            fontSans.variable
          )}
        >
          <ThemeProvider attribute="class" defaultTheme="system" enableSystem>
            <div className="relative flex min-h-screen flex-col">
              <SiteHeader />
              <div className="container mx-auto flex-1 pb-8">{children}</div>
              <div className="flex justify-end">
                <ThemeProvider2 theme={theme}>
                  <ChatBot
                    floating={true}
                    steps={steps}
                    enableSmoothScroll={true}
                    headerTitle={"SupplyControl+ Advisor"}
                    width={"500px"}
                    cache={true}
                  />
                </ThemeProvider2>
              </div>
            </div>
            <TailwindIndicator />
          </ThemeProvider>
        </body>
      </html>
    </>
  )
}
