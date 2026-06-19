# Jewelry Invoice App 💎

A beautiful Android application for generating professional invoices for jewelry businesses in India. Perfect for selling to jewelry shop owners who need to manage their inventory and create invoices with GST calculations.

## Features

✨ **Invoice Management**
- Generate professional, beautiful invoices
- Export invoices as PDF
- Track invoice history
- Manage invoice numbers automatically

💍 **Jewelry Inventory**
- Add and manage jewelry items
- Track metal type, purity, weight
- Categorize items (rings, necklaces, earrings, etc.)
- Search items by category or name

👥 **Customer Management**
- Store customer details
- Track customer history
- Search customers by name or phone

📊 **Tax Calculations**
- Indian GST Support (5%, 12%, 18%)
- Automatic tax calculations
- Category-based GST rates

🏢 **Business Settings**
- Configure business details
- Set GST number
- Bank account information
- Custom invoice prefix

## Technology Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material Design 3
- **Database**: Room Database (SQLite)
- **PDF Generation**: iText 5
- **Architecture**: MVVM + Repository Pattern
- **Async**: Kotlin Coroutines

## Getting Started

### Prerequisites

- Android Studio Giraffe or later
- JDK 17
- Android SDK API 26 and above
- Gradle 8.0+

### Installation

1. **Create a folder**
   ```bash
   mkdir jewelry-invoice-app
   cd jewelry-invoice-app
   ```

2. **Download all files** and place them in the folder structure as shown

3. **Open in Android Studio**
   - File → Open → Select the project folder
   - Let Gradle sync automatically

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run on emulator or device**
   ```bash
   ./gradlew installDebug
   ```

## Project Structure

```
jewelry-invoice-app/
├── build.gradle.kts (Root)
├── settings.gradle.kts
├── README.md
├── .gitignore
└── app/
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── src/main/
        ├── kotlin/com/jewelry/invoice/
        │   ├── MainActivity.kt
        │   ├── data/
        │   │   ├── dao/
        │   │   │   ├── JewelryItemDao.kt
        │   │   │   ├── CustomerDao.kt
        │   │   │   └── InvoiceDao.kt
        │   │   ├── database/
        │   │   │   └── JewelryDatabase.kt
        │   │   ├── entity/
        │   │   │   ├── JewelryItem.kt
        │   │   │   ├── Customer.kt
        │   │   │   ├── Invoice.kt
        │   │   │   └── BusinessSettings.kt
        │   │   └── repository/
        │   │       └── JewelryRepository.kt
        │   ├── ui/
        │   │   ├── screens/
        │   │   │   └── HomeScreen.kt
        │   │   ├── navigation/
        │   │   │   └── AppNavigation.kt
        │   │   └── theme/
        │   │       ├── Theme.kt
        │   │       └── Type.kt
        │   └── util/
        │       ├── TaxCalculator.kt
        │       ├── InvoiceNumberGenerator.kt
        │       └── PDFGenerator.kt
        └── res/
            ├── values/
            │   ├── strings.xml
            │   ├── colors.xml
            │   └── themes.xml
            └── AndroidManifest.xml
```

## How to Use

### First Time Setup

1. Open the app and go to Settings
2. Configure your business details:
   - Business name
   - GST number
   - Bank account information
   - Contact details

### Adding Jewelry Items

1. Go to "Items" section
2. Click "Add New Item"
3. Fill in details:
   - Item code and name
   - Category (Ring, Necklace, etc.)
   - Metal type (Gold, Silver, etc.)
   - Purity and weight
   - Price

### Adding Customers

1. Go to "Customers" section
2. Click "Add New Customer"
3. Enter customer information:
   - Name and phone
   - Email and address
   - GST number (if applicable)

### Creating Invoices

1. Click "Create New Invoice"
2. Select customer
3. Add items to invoice
4. Review calculations (GST is automatic)
5. Generate PDF
6. Share or save

## Features Implemented

✅ Room Database for persistence
✅ Indian GST calculations (5%, 12%, 18%)
✅ Automatic invoice numbering
✅ Beautiful PDF generation with iText
✅ Customer management
✅ Jewelry inventory tracking
✅ MVVM architecture ready
✅ Kotlin Coroutines for async
✅ Material Design 3 UI
✅ Gold/luxury theme styling

## Next Steps

After setting up the project, you can:
1. Add more screens (Invoice list, Item management, etc.)
2. Implement payment tracking
3. Add more invoice templates
4. Create business authentication
5. Add analytics and reports
6. Implement cloud sync

## Deployment

To publish on Google Play Store:

1. Create release APK:
   ```bash
   ./gradlew bundleRelease
   ```

2. Sign APK with keystore

3. Upload to Google Play Console

4. Fill metadata and launch

## Troubleshooting

### Build Issues

**Issue**: Gradle sync fails
- **Solution**: Go to File → Sync Now and ensure internet connection

**Issue**: Build error with iText
- **Solution**: The dependency is already configured in `build.gradle.kts`

### Runtime Issues

**Issue**: PDF generation fails
- **Solution**: Ensure storage permission is granted, check file path

**Issue**: Database errors
- **Solution**: Clear app data from Settings → Apps → Jewelry Invoice App

## License

MIT License - Feel free to use for commercial purposes

## Support

For issues:
- Review the code structure
- Check Android Studio logs
- Ensure all dependencies are installed

---

**Made with ❤️ for jewelry businesses**