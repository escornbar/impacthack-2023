"use client";

import "@/styles/globals.css";
import { Metadata } from "next";
import ChatBot from "react-simple-chatbot";
import { ThemeProvider as ThemeProvider2 } from "styled-components";



import { siteConfig } from "@/config/site";
import { fontSans } from "@/lib/fonts";
import { cn } from "@/lib/utils";
import { SiteHeader } from "@/components/site-header";
import { TailwindIndicator } from "@/components/tailwind-indicator";
import { ThemeProvider } from "@/components/theme-provider";
import { Inter } from "@next/font/google";





export const metadata: Metadata = {
  title: {
    default: siteConfig.name,
    template: `%s - ${siteConfig.name}`,
  },
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
        value: "How healthy is my business doing right now?",
        label: "How healthy is my business doing right now?",
        trigger: "3",
      },
    ],
  },
  {
    id: "3",
    message:
      "Overall, Tech Solutions Inc. has been performing exceptionally well. Your financial stability appears robust, with consistent revenue and profitability. Your ability to secure clients in diverse industries, such as healthcare, finance, and e-commerce, indicates a broad market reach and a strong reputation.",
    trigger: "4",
  },
  {
    id: "4",
    options: [
      {
        value: "What is my outstanding payments for the loan right now?",
        label: "What is my outstanding payments for the loan right now?",
        trigger: "5",
      },
    ],
  },
  {
    id: "5",
    message:
      "Based on our data, the current outstanding payment for your loan with Standard Chartered Bank is $347,500 across 16 monthly repayments.",
    trigger: "6",
  },
  {
    id: "6",
    options: [
      {
        value: "What types of loan is my business eligible for?",
        label: "What types of loan is my business eligible for?",
        trigger: "7",
      },
    ],
  },
  {
    id: "7",
    message:
      "Based on your credit score, you are eligible for the Working Capital Loan up to $500,000. If you are interested, you may apply via the Loans tab in the navigation bar.",
    trigger: "8",
  },
  {
    id: "8",
    options: [
      {
        value: "What can I do to improve my company's credit score?",
        label: "What can I do to improve my company's credit score?",
        trigger: "9",
      },
    ],
  },
  {
    id: "9",
    message:
      "Based on our data on your company, you may consider establishing trade lines and maintaining a stable finances to improve your company's credit score.",
    end: true,
  },
]

interface RootLayoutProps {
  children: React.ReactNode
}

const inter = Inter({
  subsets: ["latin"],
  display: "swap",
})

export default function RootLayout({ children }: RootLayoutProps) {
  return (
    <>
      <html lang="en" className={inter.className} suppressHydrationWarning>
        {/* <head /> */}
        <body
          className={cn(
            "bg-background min-h-screen font-sans antialiased",
            fontSans.variable
          )}
        >
          <ThemeProvider attribute="class" defaultTheme="system" enableSystem>
            <div className="relative flex flex-col min-h-screen">
              <SiteHeader />
              <div className="container flex-1 pb-8 mx-auto">{children}</div>
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